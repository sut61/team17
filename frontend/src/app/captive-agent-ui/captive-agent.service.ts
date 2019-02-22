import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const  captiveAgentApi = '//localhost:8080/captiveagent/';
const  genderPath = captiveAgentApi + 'gender';
const  provincePath = captiveAgentApi + 'province';

@Injectable({
  providedIn: 'root'
})
export class CaptiveAgentService {

  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });
  constructor(private http: HttpClient) { }

  public getAllgender(): Observable<any> {
    return this.http.get(genderPath , {headers: this.authKey}      );
  }
  public getAllProvince(): Observable<any> {
    return this.http.get(provincePath, {headers: this.authKey});
  }
  public getDistrictByProvince(id: number): Observable<any> {
    return this.http.get(captiveAgentApi + 'district/' + id, {headers: this.authKey});
  }
  public getSubdtrictByDistrict(id: number): Observable<any> {
    return this.http.get(captiveAgentApi + 'subdistrict/' + id, {headers: this.authKey});
  }
  public postCaptiveAgent(object: any, genderID: number, subDistrictID: number, districtID: number, provinceID: number,birthday:Date, passwordCheck:string): Observable<any> {
    return this.http.post(captiveAgentApi + genderID + '/' + subDistrictID + '/' + districtID + '/' + provinceID + '/' + birthday + '/' + passwordCheck,{
      'username': object.username,
      'password': object.password,
      'idNumber': object.idNumber,
      'firstName': object.firstName,
      'lastName': object.lastName,
      'phone': object.phone,
      'email': object.email,
      'address': {
        'address': object.address
      }
    }, {headers: this.authKey});
  }
}