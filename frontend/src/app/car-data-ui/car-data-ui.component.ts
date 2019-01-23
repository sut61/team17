import { Component, OnInit } from '@angular/core';
import { CarDataService } from './car-data.service';

@Component({
  selector: 'app-car-data-ui',
  templateUrl: './car-data-ui.component.html',
  styleUrls: ['./car-data-ui.component.css']
})
export class CarDataUiComponent implements OnInit {
  carTypeID : number;
  carTypes : Array<any>;
  branchID : number;
  branchs : Array<any>;
  colorID : number;
  colors : Array<any>;
  gearTypeID  : number;
  gearTypes  : Array<any>;
  object = {
    model:null,
    cC:null
  }
 
  constructor(private service:CarDataService) { }

  ngOnInit() {
    this.getAllCarType();
    this.getAllBranch();
    this.getAllCarColor();
    this.getAllGear();
  }
  getAllGear(){
    this.service.getGearTypes().subscribe(res=>{
      this.gearTypes =res;
    });
  }
  getAllBranch(){
    this.service.getBranchCars().subscribe(res=>{
      this.branchs =res;
    });
  }
  getAllCarColor(){
    this.service.getCarColors().subscribe(res=>{
      this.colors =res;
    });
  }
  getAllCarType(){
    this.service.getCarTypes().subscribe(res=>{
      this.carTypes =res;
    });
  }

  insertReview(){
    this.service.postCarData(this.object,this.carTypeID,this.branchID,this.colorID,this.gearTypeID).subscribe(res=>{
      console.log(res);
    })
  }
}
