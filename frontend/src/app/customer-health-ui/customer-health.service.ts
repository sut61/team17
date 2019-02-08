import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const healthApi = '//localhost:8080/health/';
const customerPath = healthApi + 'customer/';
const diseasePath = healthApi + 'disease';
const postPath = healthApi + 'post/';

@Injectable({
  providedIn: 'root'
})
export class CustomerHealthService {

  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(private http: HttpClient) {
  }

  public getCustomerByPersonalId(personalId: number): Observable<any> {
    return this.http.get(customerPath + personalId, {headers: this.authKey});
  }

  public getDiseases(): Observable<any> {
    return this.http.get(diseasePath, {headers: this.authKey});
  }

  public postCustomerHealth(object: any, diseaseID: number, idNumber: number): Observable<any> {
    return this.http.post(postPath + diseaseID + '/' + idNumber, {
      'age': object.age,
      'height': object.height,
      'weight': object.weight,
      'vivisection': object.vivisection,
      'medicine': object.medicine,
    }, {headers: this.authKey});
  }
}
