import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BeneficiaryUIComponent } from './beneficiary-ui.component';

describe('BeneficiaryUIComponent', () => {
  let component: BeneficiaryUIComponent;
  let fixture: ComponentFixture<BeneficiaryUIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BeneficiaryUIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BeneficiaryUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
