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
  maxDate = new Date();
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
    this.maxDate.setMonth(this.now.getMonth()+1);
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
  dateToString(){
    const yyyy = this.date.getFullYear();
    //prevent month from being 1 digit (yyyy-m-dd)
    const mm = (this.date.getMonth() < 10 ? '0' : '') + (this.date.getMonth() + 1);
    //prevent date from being 1 digit (yyyy-mm-d)
    const dd = (this.date.getDate() < 10 ? '0' : '') + this.date.getDate();
    //format date (yyyy-mm-dd) to string for path backend
    //And backend format string to LocalDate
    return this.periodStartDate = yyyy + '-' + mm + '-' + dd;
  }

  postPolicyData() {
      try {
        console.log(this.dateToString());
        this.service.postPolicy(this.policyObject, this.propertyIDSelected, this.customerObject.customerID, this.carDataSelected,
          sessionStorage.getItem('username'), this.periodStartDate, this.periodYear).subscribe(res => {
          console.log(res);
          alert('success');
        } , error1 => {
          alert(error1.error.message);
        });
      } catch (e){
        if (e instanceof TypeError) {
          console.log(e.message);
          if (e.message.match(".*customerID.*null"))
            alert('Id number not correct!');
          if (e.message.match(".*getFullYear.*null"))  
            alert('Please enter date before save!');
        }
      }
  }
}
