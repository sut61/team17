import {Component, OnInit} from '@angular/core';
import {ClaimService} from './claim.service';

@Component({
  selector: 'app-claim-ui',
  templateUrl: './claim-ui.component.html',
  styleUrls: ['./claim-ui.component.css']
})
export class ClaimUiComponent implements OnInit {
  carServiceSelected: number;
  carServices: Array<any>;
  claimObject = {
    claimID: '',
    dataAccident: '',
    address: ''
  };
  idCustomer: string;
  isOpenCustomer = false;
  customerObject = {
    customerID: null,
    firstName: '',
    lastName: '',
    idNumber: '',
    gender: {},
    career: {},
    address: {
      district: {},
      province: {},
      subDistrict: {}
    }
  };
  provinces: Array<any>;
  provinceSelected: number;
  claimData: any;
  claimTypes: Array<any>;
  claimTypeSelect: number;

  constructor(private claimservice: ClaimService) {
  }

  ngOnInit() {
    this.getAllProvince();
    this.getAllClaimType();
    this.getAllClaim();
  }

  searchCustomer() {
    if (typeof this.idCustomer !== 'undefined' && this.idCustomer) {
      this.claimservice.getCustomerByIdNumber(this.idCustomer).subscribe(response => {
        this.customerObject = response;
        try {
          console.log(this.customerObject.customerID);
        } catch (e) {
          if (e instanceof TypeError) {
            console.log(e.message);
            alert('Id number not correct!');
            this.isOpenCustomer = false;
          }
        }
      }, error => {
        alert('Id number not correct!');
        this.isOpenCustomer = false;
      });
      this.isOpenCustomer = true;
    } else {
      this.isOpenCustomer = false;
      alert('Please enter id number before search!');
    }
  }

  getAllProvince() {
    this.claimservice.getAllProvince().subscribe(res => {
      console.log(res);
      this.provinces = res;
    });
  }

  getAllCarservice() {
    this.claimservice.getCarserviceByProvinceID(this.provinceSelected).subscribe(res => {
      console.log(res);
      this.carServices = res;
    });
  }


  getAllClaimType() {
    this.claimservice.getAllClaimType().subscribe(res => {
      console.log(res);
      this.claimTypes = res;
    });
  }

  getAllClaim() {
    this.claimservice.getAllClaim().subscribe(res => {
      console.log(res);
      this.claimData = res;
    });
  }

  postClaimServiceData() {
    this.claimservice.postClaim(this.claimObject, this.customerObject.customerID, this.provinceSelected, this.claimTypeSelect, this.carServiceSelected).subscribe(res => {
      console.log(res);
      this.getAllClaim();
      alert('Success');
    }, error1 => {
      alert('Error');
    });
  }

}
