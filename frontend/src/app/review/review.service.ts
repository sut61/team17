import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import {TokenStorage} from '../service/token-storage';


const  postApi = '//localhost:8080/review/';
const  classPropertyPath = postApi + 'classProperty';
const  statusPath = postApi + 'status';


@Injectable({
  providedIn: 'root'
})
export class ReviewService {
public API = '//localhost:8080';
authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });

constructor(private http:HttpClient , private token: TokenStorage) { }

getAllStatus():Observable<any>{
return this.http.get(this.API + '/status', {headers: this.authKey});
}

getAllInsuType():Observable<any>{
return this.http.get(this.API + '/classProperty', {headers: this.authKey});
}

getAllCarData():Observable<any>{
return this.http.get(this.API + '/carData', {headers: this.authKey});
}

public getReviewAll():Observable<any>{
    return this.http.get(this.API + '/review', {headers: this.authKey});

  }

}
