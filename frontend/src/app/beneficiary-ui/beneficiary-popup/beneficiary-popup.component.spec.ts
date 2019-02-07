import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BeneficiaryPopupComponent } from './beneficiary-popup.component';

describe('BeneficiaryPopupComponent', () => {
  let component: BeneficiaryPopupComponent;
  let fixture: ComponentFixture<BeneficiaryPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BeneficiaryPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BeneficiaryPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
