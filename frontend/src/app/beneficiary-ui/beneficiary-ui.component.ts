import { Component, OnInit, ViewChild } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BeneficiaryService } from './beneficiary.service';
import { MatDialog, MatSnackBar, MatStepper } from '@angular/material';
import { BeneficiaryPopupComponent } from './beneficiary-popup/beneficiary-popup.component';

@Component({
  selector: 'app-beneficiary-ui',
  templateUrl: './beneficiary-ui.component.html',
  styleUrls: ['./beneficiary-ui.component.css'],
  animations: [
    trigger('showDetailPolicy', [
      state('open', style({
        opacity: 1
      })),
      state('closed', style({
        opacity: 0
      })),
      transition('open => closed', [
        animate('0.5s')
      ]),
      transition('closed => open', [
        animate('0.5s')
      ])
    ])
  ]
})
export class BeneficiaryUIComponent implements OnInit {

  static beneficiaryTemp: number;
  isOpen = true;
  beneficiaryObject = {
    firstname: null,
    lastname: null,
    phone: null,
    personalID: '',
    address: null,
    genderID: null,
    provinceID: null,
    districtID: null,
    subDistrictID: null,
    relationshipID: null,
    policyID: null
  };

  policyObject = {
    policyID: null,
    periodStartDate: null,
    periodExpiryDate: null,
    licensePlate: null,
    vin: null,
    carData: {
      branchCar: {
        branchName: ''
      },
      model: ''
    },
    customer: {
      firstName: '',
      lastName: '',
      career: {
        careerName: ''
      }
    },
    propertyPolicy: {
      propertyName: '',
      classProperty: {
        className: ''
      }
    }
  };

  beneficiaries: Array<any>;
  relationships: Array<any>;
  genders: Array<any>;
  provinces: Array<any>;
  districts: Array<any>;
  subDistricts: Array<any>;


  beneficiaryFormGroup: FormGroup;


  @ViewChild('stepper') stepper: MatStepper;


  constructor(private _formBuilder: FormBuilder,
    private service: BeneficiaryService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar) {
    this.isOpen = false;
  }

  ngOnInit() {

    this.beneficiaryFormGroup = this._formBuilder.group({
      firstname: ['', [Validators.required,Validators.pattern('^[A-Z][a-z ]*$')]],
      lastname: ['', [Validators.required,Validators.pattern('^[A-Z][a-z ]*$')]],
      personalId: ['', [Validators.required,Validators.min(13),Validators.max(13)]],
      phone: ['', [Validators.required,Validators.pattern('^[0]\\d*$'),Validators.min(9)]],
      relationship: ['', Validators.required],
      gender: ['', Validators.required],
      address: ['', Validators.required],
      province: ['', Validators.required],
      district: ['', Validators.required],
      subdistrict: ['', Validators.required]
    });

    scrollTo(0, 0);



    this.getAllProvince();
    this.getGender();
    this.getRelationship();
  }

 

  searchPolicy() {

    if (typeof this.beneficiaryObject.policyID !== 'undefined' && this.beneficiaryObject.policyID) {
      this.service.getPolicyById(this.beneficiaryObject.policyID).subscribe(response => {
        this.isOpen = true;
        this.policyObject = response;
        this.getBeneficiariesByPolicy(this.policyObject.policyID);

        /** นำข้อมูลมาใส่ใน policyObject **/
      }, error => {
        this.policyObject = null;
        this.isOpen = false;
        this.snackBar.open('การค้นหากรมธรรม์', 'ไม่สำเร็จ', {
          duration: 2000,
        });
      });

    } else {
      this.policyObject = null;
      this.isOpen = false;
    }
  }

  openDialog(bId: number): void {
    BeneficiaryPopupComponent.bId = bId;
    const dialogRef = this.dialog.open(BeneficiaryPopupComponent, {});
    dialogRef.afterClosed().subscribe(result => {
      BeneficiaryUIComponent.beneficiaryTemp = null;
      console.log('The dialog was closed');
    });
  }

  deleteBenefic(bId: number) {
    this.service.deleteBeneficiaryById(bId).subscribe(res => {
      this.snackBar.open('ลบผู้ได้รับผลประโยชน์สำเร็จ', null, {
        duration: 5000,
      });
      this.getBeneficiariesByPolicy(this.policyObject.policyID);
    }, error1 => {
      this.snackBar.open('ลบผู้ได้รับผลประโยชน์ไม่สำเร็จ', null, {
        duration: 5000,
      });
    });
  }

  post() {

    this.service.postBeneficiary(this.beneficiaryObject).subscribe(res => {
      console.log(res);
      this.getBeneficiariesByPolicy(this.policyObject.policyID);
      this.snackBar.open('เพิ่มผู้ได้รับผลประโยชน์สำเร็จ', null, {
        duration: 5000,
      });

    }, error1 => {
      console.log(error1);
      this.snackBar.open('เพิ่มผู้ได้รับผลประโยชน์ไม่สำเร็จ', null, {
        duration: 2000,
      });
    });

  }

  getBeneficiariesByPolicy(provinceID: number) {
    this.service.getBeneficiariesByPolicy(provinceID).subscribe(res => {
      this.beneficiaries = res;
    });
  }

  getRelationship() {
    this.service.getAllrelationship().subscribe(res => {
      this.relationships = res;
    });
  }

  getGender() {
    this.service.getAllgender().subscribe(res => {
      this.genders = res;
    });
  }

  getAllProvince() {
    this.service.getAllProvince().subscribe(res => {
      this.provinces = res;

    });
  }

  getAllDistrict() {
    console.log(this.beneficiaryObject.provinceID);
    this.service.getDistrictByProvince(this.beneficiaryObject.provinceID).subscribe(res => {
      this.districts = res;
      this.stepper.next();
    });
  }

  getAllSubDistrict() {

    this.service.getSubdtrictByDistrict(this.beneficiaryObject.districtID).subscribe(res => {
      this.subDistricts = res;
      this.stepper.next();
    });
  }


}
