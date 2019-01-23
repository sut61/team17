import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


/* Constant */
const carTypesPath = '//localhost:8080/carTypes'
const CarColorsPath = '//localhost:8080/carColors'
const BranchCarsPath = '//localhost:8080/branchCars'
const GearTypesPath = '//localhost:8080/gearTypes'
const postCarPath = '//localhost:8080/carData/';
@Injectable({
  providedIn: 'root'
})
export class CarDataService {

  authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

  constructor(private http: HttpClient) {
  }
  public getCarTypes():Observable<any>{
    return this.http.get(carTypesPath,{headers:this.authKey}); /** ปิดการส่ง key อยู่ **/
  }
  public getCarColors():Observable<any>{
    return this.http.get(CarColorsPath,{headers:this.authKey}); /** ปิดการส่ง key อยู่ **/
  }
  public getBranchCars():Observable<any>{
    return this.http.get(BranchCarsPath,{headers:this.authKey}); /** ปิดการส่ง key อยู่ **/
  }
  public getGearTypes():Observable<any>{
    return this.http.get(GearTypesPath,{headers:this.authKey}); /** ปิดการส่ง key อยู่ **/
  }
  public postCarData(object:any,carTypeID:number,branchCarID:number,carColorID:number,gearTypeID:number):Observable<any>{
    return this.http.post(postCarPath + carTypeID + '/' + branchCarID + '/' + carColorID + '/'+ gearTypeID,{
      'model': object.model,'cC':object.cC
    } , {headers:this.authKey});
  }
}
