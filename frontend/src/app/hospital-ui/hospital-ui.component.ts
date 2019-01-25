import {Component, OnInit} from '@angular/core';
import {HospitalService} from './hospital.service';

@Component({
  selector: 'app-hospital-ui',
  templateUrl: './hospital-ui.component.html',
  styleUrls: ['./hospital-ui.component.css']
})
export class HospitalUIComponent implements OnInit {
  hospitalBranch: any;
  displayedColumns: string[] = ['name', 'branch', 'type', 'address', 'phone'];

  object = {
    branchName: null,
    phone: null,
    address: null,
    hospital: {
      hospitalName: null
    }
  };

  types: Array<any>;
  typeSelected: number;

  address: string;
  provinces: Array<any>;
  provinceSelected: number;


  subDistricts: Array<any>;
  subDistrictSelected: number;

  districts: Array<any>;
  districtSelected: number;

  constructor(private service: HospitalService) {
  }

  ngOnInit() {
    this.getAllType();
    this.getAllProvince();
    this.getTable();
  }

  postHospital() {
    this.service.postHospital(this.object, this.typeSelected, this.provinceSelected, this.districtSelected, this.subDistrictSelected).subscribe(res => {
      console.log(res);
      this.getTable();
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
    console.log(this.provinceSelected);
    this.service.getDistrictByProvince(this.provinceSelected).subscribe(res => {
      this.districts = res;
    });
  }

  getAllType() {
    this.service.getAllType().subscribe(res => {
      this.types = res;
    });
  }

  getTable() {
    this.service.getAllBranch().subscribe(res => {
      this.hospitalBranch = res;
    });
  }
}
