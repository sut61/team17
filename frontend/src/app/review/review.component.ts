import{Component, OnInit}from '@angular/core';
import {ReviewService}from './review.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {TokenStorage} from '../service/token-storage';
@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
authKey = new HttpHeaders({
    'Authorization': sessionStorage.getItem('InsuranceAuthToken')
  });
  carDatas : Array<any>;
  insuTypes : Array<any>;
  reviewall : Array<any>;
  statuss  : Array<any>;
  Review: any = {
    classID : null,
    carID : null,
    statusID  : null,
    comment :null,
    cons :null

  };

constructor(private service: ReviewService ,private token: TokenStorage, private httpClient: HttpClient) { }

 ngOnInit() {
    this.getAllStatus();
    this.getAllInsuType();
    this.getAllReview();
    this.getAllCarData();
  }

 getAllStatus(){
    this.service.getAllStatus().subscribe(res=>{
      this.statuss =res;
      console.log(this.statuss);
    });
  }

  getAllInsuType(){
    this.service.getAllInsuType().subscribe(res=>{
      this.insuTypes =res;
      console.log(this.insuTypes);
    });
  }

  getAllCarData(){
    this.service.getAllCarData().subscribe(res=>{
      this.carDatas =res;
    });
  }


getAllReview(){
    this.service.getReviewAll().subscribe(data => {
      this.reviewall = data;
      console.log(this.reviewall);
    });
  }

  save_func() {
    this.httpClient.post('http://localhost:8080/review/' + this.Review.classID + '/' + this.Review.carID + '/' + this.Review.statusID, this.Review, {headers: this.authKey})
      .subscribe(
        data => {
          console.log('PUT Request is successful', data);
          alert('เพิ่ม Review สำเร็จ');
          location.reload();
        },
        error => {
          console.log('Rrror', error);
          alert('เกิดข้อผิดพลาด');
        }
      );
  }


}
