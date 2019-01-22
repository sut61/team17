import {Component, OnInit} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {TokenStorage} from '../service/token-storage';
import {PaymentService} from './payment.service';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-payment-ui',
  templateUrl: './payment-ui.component.html',
  styleUrls: ['./payment-ui.component.css'],
  animations: [
    trigger('showDetailPolicy', [
      state('open', style({
        opacity: 1
      })),
      state('closed', style({
        opacity: 0
      })),
      transition('open => closed', [
        animate('0.5s')
      ]),
      transition('closed => open', [
        animate('0.5s')
      ])
    ])
  ]
})
export class PaymentUIComponent implements OnInit {
  invoiceData: any;
  displayedColumns: string[] = ['invoiceID', 'invoiceAmount', 'invoiceDate', 'invoiceStatus'];

  isOpen = false;
  invoices: Array<any>;
  idPolicy: number;
  invoiceID: number;
  /**
   ใช้เก็บข้อมูลตอน GET (request อื่นๆก็ได้)มาจาก Backend แบบ JSON /
   โดยต้องตั้งชื่อตัวแปลตงกลับ Backend ด้วยในส่วนข้อมูลที่จะเอามาใช้
   */
  policyObject = {
    policyID: null,
    periodStartDate: null,
    periodExpiryDate: null,
    customer: {
      firstName: '',
      lastName: '',
      career: ''
    }
  };

  paymentObject = {
    amount: null
  };

  constructor(private token: TokenStorage,
              private paymentService: PaymentService,
              private modal: MatDialog) {

  }

  ngOnInit() {
  }


  searchPayment() {
    if (typeof this.idPolicy !== 'undefined' && this.idPolicy) {
      this.paymentService.getPolicyById(this.idPolicy).subscribe(response => {

        this.policyObject = response;
        /** นำข้อมูลมาใส่ใน policyObject **/
        this.getInvoicesOverdueByPolicy(this.idPolicy);
        this.getInvoices(this.policyObject.policyID);

      }, error => {
        this.isOpen = false;
      });
      this.isOpen = true;
    } else {
      this.isOpen = false;
    }
  }

  getInvoicesOverdueByPolicy(id: number) {
    this.paymentService.getInvoiceOverdueByPolicy(id).subscribe(response2 => {
      this.invoices = response2;
      console.log('Array : ', this.invoices.length);
    });
  }

  getInvoices(id: number) {
    this.paymentService.getAllInvoice(id).subscribe(res => {
      console.log(res);
      this.invoiceData = res;
    });
  }

  pay() {
    console.log(this.invoiceID);
    this.paymentService.postPay(this.invoiceID, 'admin', this.paymentObject).subscribe(res => {
      console.log(res, 'Success');
      this.getInvoices(this.policyObject.policyID);
      this.getInvoicesOverdueByPolicy(this.policyObject.policyID);
    }, error1 => {
      alert(error1.error.message);
      console.log(error1.error.message);
    });
  }
}

