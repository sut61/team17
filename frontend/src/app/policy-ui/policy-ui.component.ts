import {Component, OnInit} from '@angular/core';
import {PolicyService} from './policy.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-policy-ui',
  templateUrl: './policy-ui.component.html',
  styleUrls: ['./policy-ui.component.css']
})
export class PolicyUiComponent implements OnInit {
  now = new Date();
  date = new Date();
  carDataSelected:number;
  carDatas:Array<any>;
  periodStartDate: string;
  periodYear: number;
  properties: Array<any>;
  propertyIDSelected: number;
  idCustomer: string;
  idCar: number;
  branchSelected: number;
  carTypeSelected: number;
  gearTypeSelected: number;
  carColorSelected: number;
  branches: Array<any>;
  carTypes: Array<any>;
  gearTypes: Array<any>;
  carColors: Array<any>;
  isOpenProperty = false;
  isOpenCustomer = false;
  isOpenCar = false;
  propertyObject = {
    propertyID: null,
    propertyName: ''
  };

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
  carObject = {
    carID: null,
    model: '',
    cC: '',
    branchCar: {
      branchID: null,
      branchName: {}
    },
    carColor: {
      color: {}
    },
    carType: {
      carType: {}
    },
    gearType: {
      gearType: {}
    }
  };

  policyObject = {
    licensePlate: '',
    vin: ''
  };

  constructor(private service: PolicyService,
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getProperties();
    this.getAllbranches();
  }

  displayProperty() {    
    this.service.getPropertyById(this.propertyIDSelected).subscribe(response => {
      this.propertyObject = response;
      /** นำข้อมูลมาใส่ใน propertyObject **/
      this.isOpenProperty = true;
    }, error => {
      this.isOpenProperty = false;
    });
  }

  searchCustomer() {
    if (typeof this.idCustomer !== 'undefined' && this.idCustomer) {
      this.service.getCustomerByIdNumber(this.idCustomer).subscribe(response => {
        this.customerObject = response;
        try {
          console.log(this.customerObject.customerID);
        } catch (e){
          if (e instanceof TypeError) {
            console.log(e.message);    
            if (e.message.match(".*customerID.*null"))
              alert('Id number not found!');       
            this.isOpenCustomer = false;
          }
        }
      }, error => {
        alert(error.error.message);
        this.isOpenCustomer = false;
      });
      this.isOpenCustomer = true;
    } else {
      this.isOpenCustomer = false;
      this.customerObject = null;
    }
  }

  getCarData(){
    this.service.getCarData(this.branchSelected,this.carTypeSelected,this.gearTypeSelected,this.carColorSelected).subscribe(res =>{
      this.carDatas = res;
    })
  }

  getProperties() {
    this.service.getAllproperty().subscribe(res => {
      this.properties = res;
    });
  }
  getAllbranches() {
    this.service.getAllbranch().subscribe(res => {
      this.branches = res;
    });
  }
  getAllcarTypes() {
    this.service.getAllcarType().subscribe(res => {
      this.carTypes = res;
    });
  }
  getAllgearTypes() {
    this.service.getAllgearType().subscribe(res => {
      this.gearTypes = res;
    });
  }
  getAllcarColors() {
    this.service.getAllcarColor().subscribe(res => {
      this.carColors = res;
    });
  }  

  postPolicyData() {
    if(this.date == null){
      this.snackBar.open('Please enter date before save!', null, {
        duration: 5000,
      });
    }else if(this.customerObject == null){
      this.snackBar.open('Id number not correct!', null, {
        duration: 5000,
      });
    }else{  
        this.service.postPolicy(this.policyObject, this.propertyIDSelected, this.customerObject.customerID, this.carDataSelected,
          sessionStorage.getItem('username'), this.date, this.periodYear).subscribe(res => {
          console.log(res);
          this.snackBar.open('success', null, {
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
