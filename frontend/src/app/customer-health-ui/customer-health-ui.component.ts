import {Component, OnInit} from '@angular/core';
import {CustomerHealthService} from './customer-health.service';
import { MatSnackBar} from '@angular/material';
@Component({
  selector: 'app-customer-health-ui',
  templateUrl: './customer-health-ui.component.html',
  styleUrls: ['./customer-health-ui.component.css']
})
export class CustomerHealthUIComponent implements OnInit {
  idNumber: number;
  isOpenCustomer = false;
  diseases: Array<any>;
  diseaseId: number;
  healthobject = {
    age: '',
    height: '',
    weight: '',
    vivisection: '',
    medicine: '',
  };
  customerObject = {
    customerID: null,
    firstName: '',
    lastName: '',
    idNumber: ''
  };

  constructor(private  service: CustomerHealthService,private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getDiseases();
  }

  getCustomer() {
    if (typeof this.idNumber !== 'undefined' && this.idNumber) {
    this.service.getCustomerByPersonalId(this.idNumber).subscribe(res => {
      this.customerObject = res;
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

  getDiseases() {
    this.service.getDiseases().subscribe(res => {
      this.diseases = res;
      console.log(res);
    });
  }

  postHealth() {
    this.service.postCustomerHealth(this.healthobject, this.diseaseId, this.idNumber).subscribe(res => {
      console.log(res);
      this.snackBar.open('บันทึกข้อมูลสำเร็จ', null, {
        duration: 6000,
      });

    }, error1 => {
      console.log(error1);
      this.snackBar.open('บันทึกข้อมูลไม่สำเร็จ', null, {
        duration: 3000,
      });
    });
  }
}
