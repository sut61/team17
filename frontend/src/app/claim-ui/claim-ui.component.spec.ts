import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimUiComponent } from './claim-ui.component';

describe('ClaimUiComponent', () => {
  let component: ClaimUiComponent;
  let fixture: ComponentFixture<ClaimUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClaimUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
