import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

/* Constant */

const propertyApi = '//localhost:8080/propertyPolicy/';
const classPath = propertyApi + 'classProperty';

@Injectable({
  providedIn: 'root'
})
export class PropertyPolicyUiService {
  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });
  constructor(private http: HttpClient) { }


  public getAllClassPolicy(): Observable<any> {
    return this.http.get(classPath, {headers: this.authKey});
  }


  public getAllTableData(): Observable<any> {
    return this.http.get(propertyApi , {headers: this.authKey});
  }
  public postPropertyPolicy(object: any, classID: number ): Observable<any> {
    return this.http.post(propertyApi + classID , {
      'propertyName': object.propertyName,
      'detailProtection': object.detailProtection,
      'detailPayment': object.detailPayment,
      'costPolicy' : object.costPolicy,
    }, {headers: this.authKey});
  }


}
