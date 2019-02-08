import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const claimAPI = '//localhost:8080/claim/';
const carservicePath = claimAPI + 'carservice/';
const customerPath = claimAPI + 'customer/';
const provincePath = claimAPI + 'province';
const claimTypePath = claimAPI + 'claimtype';

@Injectable({
  providedIn: 'root'
})
export class ClaimService {
  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(private http: HttpClient) {
  }

  public getAllProvince(): Observable<any> {
    return this.http.get(provincePath, {headers: this.authKey});
  }
  public getCarserviceByProvinceID(provinceId: number): Observable<any> {
    return this.http.get(carservicePath + provinceId, {headers: this.authKey});
  }


  public getAllClaim(): Observable<any> {
    return this.http.get(claimAPI, {headers: this.authKey});
  }

  public getAllClaimType(): Observable<any> {
    return this.http.get(claimTypePath, {headers: this.authKey});
  }

  public postClaim(object: any, customerID: number, provinceID: number, claimTypeID: number, carServiceID: number): Observable<any> {
    return this.http.post(claimAPI + customerID + '/' + provinceID + '/' + claimTypeID + '/' + carServiceID, {
      'dataAccident': object.dataAccident
    }, {headers: this.authKey});
  }

  public getCustomerByIdNumber(idNumber: string): Observable<any> {
    console.log('Call PolicyService method : getCustomerByIdNumber(' + idNumber + ') Rest API : ' + customerPath + idNumber);
    return this.http.get(customerPath + idNumber, {headers: this.authKey});
    /** ปิดการส่ง key อยู่ **/
  }
}
