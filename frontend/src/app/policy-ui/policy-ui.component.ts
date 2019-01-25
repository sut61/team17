import {Component, OnInit} from '@angular/core';
import {PolicyService} from './policy.service';

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
  licensePlate: string;
  vIn: string;
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
  };

  constructor(private service: PolicyService) {
  }

  ngOnInit() {
    this.getProperties();
    this.getAllbranches();
  }

  myFilter = (d: Date): boolean => {
    const day = d.getDate();
    const month = d.getMonth();
    const year = d.getFullYear();
    // Prevent previous date from being selected.
    return (day >= this.now.getDate() && month == this.now.getMonth() && year == this.now.getFullYear())
      || (month > this.now.getMonth() && year == this.now.getFullYear()
        || (year > this.now.getFullYear()));
  };

  displayProperty() {
    this.isOpenProperty = true;
    this.service.getPropertyById(this.propertyIDSelected).subscribe(response => {
      this.propertyObject = response;
      /** นำข้อมูลมาใส่ใน propertyObject **/
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
      alert('Please enter id number before search!')
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
    if(this.propertyIDSelected == null){
      alert('Please select property before save!');
    }else if(this.customerObject.customerID == null){
      alert('Please click search before save!');      
    }else{     
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
          alert('Please enter date before save!');
        }
      }  
    }
  }
}