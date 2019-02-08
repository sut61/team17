import {Component, OnInit} from '@angular/core';
import {CustomerInfoService} from './customer-info.service';

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

  customerObject = {
    firstName: null,
    lastName: null,
    idNumber: null,
    email: null,
    birthday: null,
    phone: null,
    address: null
  };

  maxDate = new Date(new Date().getFullYear() - 18, new Date().getMonth() , new Date().getDate());
  bd = new Date();



  constructor(private service: CustomerInfoService) {
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
    this.customerObject.birthday = this.bd.getFullYear() + '-' + this.bd.getMonth() + '-' + this.bd.getDate() ;
    this.service.postCustomer(this.customerObject, this.careerIDselected, this.genderIDSelected, this.subDistrictSelected, this.districtSelected, this.provinceSelected).subscribe(res => {
        console.log(res);
        alert('บันทึกสำเร็จ');
      }
      , error1 => {
        alert('กรุณากรองข้อมูลให้ถูกต้องและครบถ้วน');
      });
  }
test() {
    console.log(this.bd.getFullYear() + '-' + (this.bd.getMonth() + 1) + '-' + this.bd.getDate() );

}

}
