import { Component, OnInit } from '@angular/core';
import {CaptiveAgentService} from './captive-agent.service';
import { MatSnackBar } from '@angular/material';
@Component({
  selector: 'app-captive-agent-ui',
  templateUrl: './captive-agent-ui.component.html',
  styleUrls: ['./captive-agent-ui.component.css']
})
export class CaptiveAgentUiComponent implements OnInit {
  genders: Array<any>;
  genderIDSelected: number;
  provinces: Array<any>;
  provinceSelected: number;
  subDistricts: Array<any>;
  subDistrictSelected: number;
  districts: Array<any>;
  districtSelected: number;
  now = new Date();
  date = null;
  birthday: string;
  passwordCheck: string;
  captiveAgentObject = {
    username: null,
    password: null,
    idNumber: null,
    firstName: null,
    lastName: null,
    phone: null,
    email: null,
    birthday:null,
    address: null
  }
  constructor(private service: CaptiveAgentService,
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getGender();
    this.getAllProvince();    
  }
  getGender() {
    this.service.getAllgender().subscribe(res => {
      this.genders = res;
    });
  }
  getAllProvince() {
    this.service.getAllProvince().subscribe(res => {
      this.provinces = res;
    });
  }

  getAllSubDistrict() {
    this.service.getSubdtrictByDistrict(this.districtSelected).subscribe(res => {
      this.subDistricts = res;
    });
  }

  getAllDistrict() {
    this.service.getDistrictByProvince(this.provinceSelected).subscribe(res => {
      this.districts = res;
    });
  }

  postCaptiveAgentData() {    
      if(this.date == null){
        this.snackBar.open('Please enter date before save!', null, {
          duration: 5000,
        });
      }else{      
      this.service.postCaptiveAgent(this.captiveAgentObject, this.genderIDSelected, this.subDistrictSelected, this.districtSelected,
        this.provinceSelected,this.date,this.passwordCheck).subscribe(res => {
          console.log(res);
          this.snackBar.open('Success', null, {
            duration: 5000,
          });
      } , error1 => {
          this.snackBar.open(error1.error.message, null, {
            duration: 5000,
          });
      });         
    }              
  }
}