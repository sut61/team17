import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


const hospaitalApi = '//localhost:8080/hospitalApi/';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {
  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(private http: HttpClient) {
  }

  public getAllType(): Observable<any> {
    return this.http.get(hospaitalApi + 'type', {headers: this.authKey});
  }

  public getAllProvince(): Observable<any> {
    return this.http.get(hospaitalApi + 'province', {headers: this.authKey});
  }

  public getDistrictByProvince(id: number): Observable<any> {
    return this.http.get(hospaitalApi + 'district/' + id, {headers: this.authKey});
  }

  public getSubdtrictByDistrict(id: number): Observable<any> {
    return this.http.get(hospaitalApi + 'subdistrict/' + id, {headers: this.authKey});
  }
  public getAllBranch(): Observable<any>{
    return this.http.get(hospaitalApi + 'branch', {headers: this.authKey});
  }

  public postHospital(object: any, typeID: number, provinceID: number, districtID: number, subDistrictID: number): Observable<any> {
    return this.http.post(hospaitalApi + 'hospital/' + typeID + '/' + provinceID + '/' + districtID + '/' + subDistrictID, {
      'branchName': object.branchName,
      'phone': object.phone,
      'address': {
        'address': object.address
      },
      'hospital': {
        'hospitalName': object.hospital.hospitalName
      }
    }, {headers: this.authKey});
  }
}
