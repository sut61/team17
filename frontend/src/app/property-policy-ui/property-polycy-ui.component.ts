import {Component, OnInit} from '@angular/core';
import { MatSnackBar} from '@angular/material';
import {PropertyPolicyUiService} from './property-policy-ui.service';

@Component({
  selector: 'app-property-polycy-ui',
  templateUrl: './property-polycy-ui.component.html',
  styleUrls: ['./property-polycy-ui.component.css']
})
export class PropertyPolycyUiComponent implements OnInit {

  propertyData: any;
  displayedColumns: string[] = ['name', 'class', 'detailProtection', 'detailPayment',  'cost'];


  classPropertys: Array<any>;
  classIDselect: number;

  propertyObject = {
    propertyName: null,
    detailProtection : null,
    detailPayment: null,
    costPolicy: null
  };

  constructor(private propertyService: PropertyPolicyUiService , private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getClassData();

    this.getTable();
  }

  getClassData() {
    this.propertyService.getAllClassPolicy().subscribe(res => {
      this.classPropertys = res;
    });
  }

  postPropertyData() {
    this.propertyService.postPropertyPolicy(this.propertyObject, this.classIDselect).subscribe(res => {
        console.log(res);
      this.snackBar.open('เพิ่มประเภทประกันรถสำเร็จ', null, {
        duration: 5000,
      });

    }, error1 => {
      console.log(error1);
      this.snackBar.open('เพิ่มประเภทประกันรถไม่สำเร็จ กรุณากรอกข้อมมูลให้ถูกต้องและครบถ้วน', null, {
        duration: 2000,
      });
    });
  }

  getTable() {
    this.propertyService.getAllTableData().subscribe(res => {
      this.propertyData = res;
    });
  }
}
