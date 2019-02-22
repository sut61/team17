import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const policyApi = '//localhost:8080/policy/';
const propertyPath = policyApi + 'property/';
const customerPath = policyApi + 'customer/';
const carPath = policyApi + 'carDatas/';
const branchPath = policyApi + 'branchCars/';
const carTypePath = policyApi + 'carTypes';
const gearTypePath = policyApi + 'gearTypes';
const carColorPath = policyApi + 'carColors';

@Injectable({
  providedIn: 'root'
})
export class PolicyService {
  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(private http: HttpClient) {
  }

  public getCarData(branchId: number, carTypeID: number, gearTypeID: number, carColorID: number): Observable<any> {
    return this.http.get(carPath + branchId + '/' + carColorID + '/' + carTypeID + '/' + gearTypeID, {headers: this.authKey});
  }

  public getAllproperty(): Observable<any> {
    return this.http.get(propertyPath, {headers: this.authKey});
  }

  public getAllbranch(): Observable<any> {
    return this.http.get(branchPath, {headers: this.authKey});
  }

  public getAllcarType(): Observable<any> {
    return this.http.get(carTypePath, {headers: this.authKey});
  }

  public getAllgearType(): Observable<any> {
    return this.http.get(gearTypePath, {headers: this.authKey});
  }

  public getAllcarColor(): Observable<any> {
    return this.http.get(carColorPath, {headers: this.authKey});
  }

  public getPropertyById(id: number): Observable<any> {
    console.log('Call PolicyService method : getPropertyById(' + id + ') Rest API : ' + propertyPath + id);
    return this.http.get(propertyPath + id, {headers: this.authKey});
  }

  public getCustomerByIdNumber(idNumber: string): Observable<any> {
    console.log('Call PolicyService method : getCustomerByIdNumber(' + idNumber + ') Rest API : ' + customerPath + idNumber);
    return this.http.get(customerPath + idNumber, {headers: this.authKey});
  }

  public getCarById(id: number): Observable<any> {
    console.log('Call PolicyService method : getCarById(' + id + ') Rest API : ' + carPath + id);
    return this.http.get(carPath + id, {headers: this.authKey});
  }

  public postPolicy(object: any, propertyID: number, customerID: number, carID: number, username: string, periodStartDate: Date, periodYear: number): Observable<any> {
    return this.http.post(policyApi + propertyID + '/' + customerID + '/' + carID + '/' + username + '/' + periodStartDate + '/' + periodYear, {
      'licensePlate': object.licensePlate,
      'vin': object.vin
    }, {headers: this.authKey});
  }
}
