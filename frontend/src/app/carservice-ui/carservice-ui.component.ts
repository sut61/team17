import {Component, OnInit} from '@angular/core';
import {CarserviceService} from './carservice.service';

@Component({
  selector: 'app-carservice-ui',
  templateUrl: './carservice-ui.component.html',
  styleUrls: ['./carservice-ui.component.css']
})
export class CarserviceUiComponent implements OnInit {
  carServiceObject = {
    carServiceID: '',
    carServiceName: '',
    address: ''
  };

  carServiceData: any;


  provinces: Array<any>;
  provinceSelected: number;


  subDistricts: Array<any>;
  subDistrictSelected: number;

  districts: Array<any>;
  districtSelected: number;
  carServiceTypes: Array<any>;
  carServiceTypeSelected: number;

  displayedColumns: string[] = ['carServiceName', 'address', 'carServiceType'];

  constructor(private carservice: CarserviceService) {
  }

  ngOnInit() {
    this.getAllProvince();
    this.getAllCarServiceType();
    this.getAllCarService();
  }

  getAllProvince() {
    this.carservice.getAllProvince().subscribe(res => {
      this.provinces = res;
    });
  }

  getAllCarServiceType() {
    this.carservice.getAllCarServiceType().subscribe(res => {
      console.log(res);
      this.carServiceTypes = res;
    });
  }

  getAllSubDistrict() {

    this.carservice.getSubdtrictByDistrict(this.districtSelected).subscribe(res => {
      this.subDistricts = res;

    });
  }

  getAllDistrict() {
    console.log(this.provinceSelected);
    this.carservice.getDistrictByProvince(this.provinceSelected).subscribe(res => {
      this.districts = res;
    });
  }

  postCarServiceData() {
    this.carservice.postCarService(this.carServiceObject, this.subDistrictSelected, this.districtSelected, this.provinceSelected, this.carServiceTypeSelected).subscribe(res => {
      console.log(res);
      this.getAllCarService();
    });
  }

  getAllCarService() {
    this.carservice.getAllCarService().subscribe(res => {
      this.carServiceData = res;
    });
  }

  test(row: any) {
    this.carServiceObject.address = row.address.address;
    console.log(row);
  }
}
