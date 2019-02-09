import{Component, OnInit}from '@angular/core';
import {ReviewService}from './review.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar} from '@angular/material';
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

constructor(private service: ReviewService ,private token: TokenStorage, private httpClient: HttpClient,private snackBar: MatSnackBar) { }

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
         this.snackBar.open('เพิ่มรีวิวสำเร็จ', null, {
        duration: 6000,
      });

    }, error1 => {
      console.log(error1);
      this.snackBar.open('เพิ่มรีวิวไม่สำเร็จ', null, {
        duration: 3000,
      });
    });
  }


}
