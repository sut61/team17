import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


const paymentApi = '//localhost:8080/payment/';
const searchPolicy = paymentApi + 'search-policy/';
const beneficiaryApi = '//localhost:8080/beneficiary/';
const postPath = beneficiaryApi + 'post/';
const relationshipPath = beneficiaryApi + 'relationships';
const genderPath = beneficiaryApi + 'gender';
const provincePath = beneficiaryApi + 'province';
const districtPath = beneficiaryApi + 'district/';
const subdistrictPath = beneficiaryApi + 'subdistrict/';

@Injectable({
  providedIn: 'root'
})
export class BeneficiaryService {
  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(private http: HttpClient) {
  }

  public getPolicyById(id: number): Observable<any> {
    console.log('Call PaymentService method : getPolicyById(' + id + ') Rest API : ' + searchPolicy + id);
    return this.http.get(searchPolicy + id, {headers: this.authKey});
    /** ปิดการส่ง key อยู่ **/
  }

  public getBeneficiaryById(id: number): Observable<any> {
    return this.http.get(beneficiaryApi + 'bId/' + id, {headers: this.authKey});
    /** ปิดการส่ง key อยู่ **/
  }

  public deleteBeneficiaryById(id: number): Observable<any> {
    return this.http.delete(beneficiaryApi + 'bId/' + id, {headers: this.authKey});
    /** ปิดการส่ง key อยู่ **/
  }


  public postBeneficiary(object: any): Observable<any> {
    return this.http.post(postPath + object.policyID + '/' + object.genderID + '/' + object.relationshipID + '/' + object.provinceID + '/' + object.districtID + '/' + object.subDistrictID, {
      'firstname': object.firstname,
      'lastname': object.lastname,
      'phone': object.phone,
      'personalId': object.personalID,
      'address': {
        'address': object.address
      }
    }, {headers: this.authKey});

  }

  public getBeneficiariesByPolicy(policyId: number): Observable<any> {
    return this.http.get(beneficiaryApi + policyId, {headers: this.authKey});
  }

  public getAllrelationship(): Observable<any> {
    return this.http.get(relationshipPath, {headers: this.authKey});
  }

  public getAllgender(): Observable<any> {
    return this.http.get(genderPath, {headers: this.authKey});
  }

  public getAllProvince(): Observable<any> {
    return this.http.get(provincePath, {headers: this.authKey});
  }

  public getDistrictByProvince(id: number): Observable<any> {
    return this.http.get(districtPath + id, {headers: this.authKey});
  }

  public getSubdtrictByDistrict(id: number): Observable<any> {
    return this.http.get(subdistrictPath + id, {headers: this.authKey});
  }
}
