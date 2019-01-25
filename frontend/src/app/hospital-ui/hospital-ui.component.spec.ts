import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalUIComponent } from './hospital-ui.component';

describe('HospitalUIComponent', () => {
  let component: HospitalUIComponent;
  let fixture: ComponentFixture<HospitalUIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HospitalUIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
