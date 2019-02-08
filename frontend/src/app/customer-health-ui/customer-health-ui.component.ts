import {Component, OnInit} from '@angular/core';
import {CustomerHealthService} from './customer-health.service';

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
    firstName: '',
    lastName: '',
    idNumber: ''
  };

  constructor(private  service: CustomerHealthService) {
  }

  ngOnInit() {
    this.getDiseases();
  }

  getCustomer() {
    this.service.getCustomerByPersonalId(this.idNumber).subscribe(res => {
      this.customerObject = res;
      console.log(res);
      this.isOpenCustomer = true;
    });
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
      alert('บันทึกข้อมูลสำเร็จ');
    }, error1 => {
      alert('บันทึกข้อมูลไม่สำเร็จ');
    });
  }
}
