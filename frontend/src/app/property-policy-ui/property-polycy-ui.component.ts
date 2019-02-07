import {Component, OnInit} from '@angular/core';
import {CustomerInfoService} from '../customer-info-ui/customer-info.service';
import {PropertyPolicyUiService} from './property-policy-ui.service';

@Component({
  selector: 'app-property-polycy-ui',
  templateUrl: './property-polycy-ui.component.html',
  styleUrls: ['./property-polycy-ui.component.css']
})
export class PropertyPolycyUiComponent implements OnInit {

  propertyData: any;
  displayedColumns: string[] = ['name', 'detail', 'class', 'cost'];


  classPropertys: Array<any>;
  classIDselect: number;

  propertyObject = {
    propertyName: null,
    detail: null,
    costPolicy: null
  };

  constructor(private propertyService: PropertyPolicyUiService) {
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
        alert('เพิ่มสำเร็จ');
      }
      , error1 => {
        alert('กรุณากรองข้อมูลให้ถูกต้องและครบถ้วน');
      });
  }

  getTable() {
    this.propertyService.getAllTableData().subscribe(res => {
      this.propertyData = res;
    });
  }
}
