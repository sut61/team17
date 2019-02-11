import {Component, OnInit} from '@angular/core';
import {CustomerInfoService} from './customer-info.service';
import { MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-customer-info-ui',
  templateUrl: './customer-info-ui.component.html',
  styleUrls: ['./customer-info-ui.component.css']
})
export class CustomerInfoUIComponent implements OnInit {
  careers: Array<any>;
  careerIDselected: number;
  genders: Array<any>;
  genderIDSelected: number;
  provinces: Array<any>;
  provinceSelected: number;
  subDistricts: Array<any>;
  subDistrictSelected: number;
  districts: Array<any>;
  districtSelected: number;
  birthday: string;

  customerObject = {
    firstName: null,
    lastName: null,
    idNumber: null,
    email: null,

    phone: null,
    address: null
  };

  maxDate = new Date(new Date().getFullYear() - 18, new Date().getMonth() , new Date().getDate());
  bd = new Date();



  constructor(private service: CustomerInfoService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getCareers();
    this.getGender();
    this.getAllProvince();
  }

  getCareers() {
    this.service.getAllCareer().subscribe(res => {
      this.careers = res;
    });
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

  postCustomerData() {
    console.log(this.dateToString());
    this.service.postCustomer(this.customerObject, this.careerIDselected, this.genderIDSelected, this.subDistrictSelected, this.districtSelected, this.provinceSelected, this.birthday).subscribe(res => {
      this.snackBar.open('เพิ่มข้อมูลลูกค้าสำเร็จ', null, {
        duration: 5000,
      });

    }, error1 => {
      console.log(error1);
      this.snackBar.open('เพิ่มข้อมูลลูกค้าไม่สำเร็จ กรุณากรอกข้อมมูลให้ถูกต้องและครบถ้วน', null, {
        duration: 2000,
      });
    });
  }

  dateToString() {
    const yyyy = this.bd.getFullYear();
    /*//prevent month from being 1 digit (yyyy-m-dd)*/
    const mm = (this.bd.getMonth() < 10 ? '0' : '') + (this.bd.getMonth() + 1);
    /*//prevent date from being 1 digit (yyyy-mm-d)*/
    const dd = (this.bd.getDate() < 10 ? '0' : '') + this.bd.getDate();
  /*  //format date (yyyy-mm-dd) to string for path backend
    //And backend format string to LocalDate*/
    return this.birthday = yyyy + '-' + mm + '-' + dd;
  }
}
