import { Component, OnInit } from '@angular/core';
import { CarDataService } from './car-data.service';
import { MatSnackBar} from '@angular/material';
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

  constructor(private service:CarDataService,private snackBar: MatSnackBar) { }

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
    this.service.postCarData(this.object,this.carTypeID,this.branchID,this.colorID,this.gearTypeID)
    .subscribe(
      data => {
          console.log('PUT Request is successful', data);
         this.snackBar.open('เพิ่มรีวิวสำเร็จ', null, {
        duration: 6000,
      });


    }, error1 => {
      console.log(error1);
      this.snackBar.open('เพิ่มรีวิวไม่สำเร็จ', null, {
        duration: 3000,
      });
    });
  }
}
