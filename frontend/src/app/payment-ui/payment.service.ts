import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorage} from '../service/token-storage';


/** กุญแจสำหรับ ปลดล็อก Spring Security  ทุกคนต้องมี key /
 ในการ Get post และ request อื่นๆ ตอนนี้ปิดอยู่เด้อ Backend /
 ก้ปิดอยู่ เดี่ยวค่อยเปิดทำหน้าเว็ปของตัวเองไปก่อน*/
/*const authKey = new HttpHeaders({
  'Authorization': sessionStorage.getItem('InsuranceAuthToken')
});*/


/* Constant */
const paymentApi = '//localhost:8080/payment/';
const searchPolicy = paymentApi + 'search-policy/';
const searchInvoiceAll = paymentApi + 'search-invoice-all/';
const searchInvoiceOverdue = paymentApi + 'search-invoice-overdue/';
const postPay = paymentApi + 'pay/';


@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(public http: HttpClient, private token: TokenStorage) {

  }

  public getPolicyById(id: number): Observable<any> {
    console.log('Call PaymentService method : getPolicyById(' + id + ') Rest API : ' + searchPolicy + id);
    return this.http.get(searchPolicy + id, {headers: this.authKey});
    /** ปิดการส่ง key อยู่ **/
  }

  public getInvoiceOverdueByPolicy(id: number): Observable<any> {
    return this.http.get(searchInvoiceOverdue + id, {headers: this.authKey}); /** ปิดการส่ง key อยู่ **/
  }

  public postPay(invoiceID: number, empUser: string, paymentObject: any): Observable<any> {
    return this.http.post(postPay + invoiceID + '/' + empUser, {
      'amount': paymentObject.amount
    }, {headers: this.authKey});
  }

  public getAllInvoice(id: number): Observable<any> {
    return this.http.get(searchInvoiceAll + id, {headers: this.authKey});
  }
}
