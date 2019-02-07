import {Component, OnInit} from '@angular/core';
import {BeneficiaryUIComponent} from '../beneficiary-ui.component';
import {BeneficiaryService} from '../beneficiary.service';

@Component({
  selector: 'app-beneficiary-popup',
  templateUrl: './beneficiary-popup.component.html',
  styleUrls: ['./beneficiary-popup.component.css']
})
export class BeneficiaryPopupComponent implements OnInit {
  beneficiaryObject = {
    firstname: '',
    lastname: '',
    phone: '',
    personalId: '',
    gender: {
      genderType: ''
    },
    relationship: '',
    address: {
      address: '',
      district: {
        districtName: ''
      },
      subDistrict: {
        subDistrictName: ''
      },
      province: {
        provinceName: ''
      }
    }
  };

  constructor(private service: BeneficiaryService) {

  }

  ngOnInit() {
    this.service.getBeneficiaryById(BeneficiaryUIComponent.beneficiaryTemp).subscribe(res => {
      this.beneficiaryObject = res;
    });

  }

}
