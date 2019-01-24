import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


/* Constant */
const carserviceApi = '//localhost:8080/carservice/';
const provincePath = carserviceApi + 'province';
const serviceTypePath = carserviceApi + 'car-service-type';

@Injectable({
  providedIn: 'root'
})
export class CarserviceService {
  /** กุญแจสำหรับ ปลดล็อก Spring Security  ทุกคนต้องมี key /
   ในการ Get post และ request อื่นๆ ตอนนี้ปิดอยู่เด้อ Backend /
   ก้ปิดอยู่ เดี่ยวค่อยเปิดทำหน้าเว็ปของตัวเองไปก่อน**/

  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });
  constructor(private http: HttpClient) {
  }

  public getAllCarService(): Observable<any> {
    return this.http.get(carserviceApi, {headers: this.authKey});
  }
  public getAllCarServiceType(): Observable<any> {
    return this.http.get(serviceTypePath, {headers: this.authKey});
  }

  public getAllProvince(): Observable<any> {
    return this.http.get(provincePath, {headers: this.authKey});
  }

  public postCarService(object: any, subdistrictID: number, districtID: number, proviceID: number, carservicetypeID: number): Observable<any> {
    return this.http.post(carserviceApi + subdistrictID + '/' + districtID + '/' + proviceID + '/' + carservicetypeID, {
      'carServiceName': object.carServiceName,
      'address': {
        'address': object.address
      }
    }, {headers: this.authKey});
  }


  public getDistrictByProvince(id: number): Observable<any> {
    return this.http.get(carserviceApi + 'district/' + id, {headers: this.authKey});
  }

  public getSubdtrictByDistrict(id: number): Observable<any> {
    return this.http.get(carserviceApi + 'subdistrict/' + id, {headers: this.authKey});
  }
}
