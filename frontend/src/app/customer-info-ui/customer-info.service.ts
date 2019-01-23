import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


/* Constant */
const  customerApi = '//localhost:8080/customer/';
const  careerPath = customerApi + 'career';
const  genderPath = customerApi + 'gender';
const  provincePath = customerApi + 'province';

@Injectable({
  providedIn: 'root'
})
export class CustomerInfoService {
  /** กุญแจสำหรับ ปลดล็อก Spring Security  ทุกคนต้องมี key /
   ในการ Get post และ request อื่นๆ ตอนนี้ปิดอยู่เด้อ Backend /
   ก้ปิดอยู่ เดี่ยวค่อยเปิดทำหน้าเว็ปของตัวเองไปก่อน**/

  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(private http: HttpClient) {}

  public getAllCareer(): Observable<any> {
    return this.http.get(careerPath , {headers: this.authKey});
  }

  public getAllgender(): Observable<any> {
    return this.http.get(genderPath , {headers: this.authKey});
  }

  public getAllProvince(): Observable<any> {
    return this.http.get(provincePath, {headers: this.authKey});
  }

  public getDistrictByProvince(id: number): Observable<any> {
    return this.http.get(customerApi + 'district/' + id, {headers: this.authKey});
  }

  public getSubdtrictByDistrict(id: number): Observable<any> {
    return this.http.get(customerApi + 'subdistrict/' + id, {headers: this.authKey});
  }
  public postCustomer(object: any, careerID: number, genderID: number, subdistrictID: number, districtID: number, proviceID: number): Observable<any> {
    return this.http.post(customerApi + careerID + '/' + genderID + '/' + subdistrictID + '/' + districtID + '/' + proviceID , {
      'firstName': object.firstName,
      'lastName': object.lastName,
      'idNumber' : object.idNumber,
      'email' : object.email,
      'birthday' : object.birthday,
      'phone' : object.phone,
      'address': {
        'address': object.address
      }
    }, {headers: this.authKey});


  }
}
