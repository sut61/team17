import { Component, OnInit } from '@angular/core';
import {CaptiveAgentService} from './captive-agent.service';
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
  maxDate = new Date();
  now = new Date();
  date = new Date();
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
    address: null
  }
  constructor(private service: CaptiveAgentService) { }

  ngOnInit() {
    this.getGender();
    this.getAllProvince();    
    this.maxDate.setFullYear(this.now.getFullYear() - 18);
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

  dateToString(){
    const yyyy = this.date.getFullYear();
    //prevent month from being 1 digit (yyyy-m-dd)
    const mm = (this.date.getMonth() < 10 ? '0' : '') + (this.date.getMonth() + 1);
    //prevent date from being 1 digit (yyyy-mm-d)
    const dd = (this.date.getDate() < 10 ? '0' : '') + this.date.getDate();
    //format date (yyyy-mm-dd) to string for path backend
    //And backend format string to LocalDate
    return this.birthday = yyyy + '-' + mm + '-' + dd;
  }

  postCaptiveAgentData() {
    // if(this.propertyIDSelected == null){
    //   alert('Please select property before save!');
    // }else if(this.customerObject.customerID == null){
    //   alert('Please click search before save!');
    // }else if(this.periodYear == null){
    //   alert('Please select period before save!');    
    // }else if(this.carDataSelected == null){
    //   alert('Please select car data before save!');
    // }else{
      // try {
        console.log(this.dateToString());
        this.service.postCaptiveAgent(this.captiveAgentObject, this.genderIDSelected, this.subDistrictSelected, this.districtSelected,
          this.provinceSelected,this.birthday,this.passwordCheck).subscribe(res => {
          console.log(res);
          alert('success');
        } , error1 => {
          alert(error1.error.message);
        });
      // } catch (e){
      //   if (e instanceof TypeError) {
      //     console.log(e.message);
      //     alert('Please enter date before save!');
      //   }
      // }
  //   }
  }
}