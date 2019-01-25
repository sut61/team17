import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PolicyUiComponent } from './policy-ui.component';

describe('PolicyUiComponent', () => {
  let component: PolicyUiComponent;
  let fixture: ComponentFixture<PolicyUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PolicyUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PolicyUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
