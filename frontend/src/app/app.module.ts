import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {RouterModule, Routes} from '@angular/router';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
} from '@angular/material';
import {HomeComponent} from './home/home.component';
import {LoginUIComponent} from './login-ui/login-ui.component';
import {PaymentUIComponent} from './payment-ui/payment-ui.component';
import {CarDataUiComponent} from './car-data-ui/car-data-ui.component';
import {CustomerInfoUIComponent} from './customer-info-ui/customer-info-ui.component';
import {CarserviceUiComponent} from './carservice-ui/carservice-ui.component';
import {AuthGuardService} from './service/auth-guard.service';
import {HospitalUIComponent} from './hospital-ui/hospital-ui.component';
import {PolicyUiComponent} from './policy-ui/policy-ui.component';
import {PropertyPolycyUiComponent} from './property-policy-ui/property-polycy-ui.component';
import {BeneficiaryUIComponent} from './beneficiary-ui/beneficiary-ui.component';
import {BeneficiaryPopupComponent} from './beneficiary-ui/beneficiary-popup/beneficiary-popup.component';
import { ReviewComponent } from './review/review.component';

import {ClaimUiComponent} from './claim-ui/claim-ui.component';
import {CustomerHealthUIComponent} from './customer-health-ui/customer-health-ui.component';

const routes: Routes = [
  {path: 'payment', component: PaymentUIComponent, canActivate: [AuthGuardService]},
  {path: 'login', component: LoginUIComponent},
  {path: 'customer', component: CustomerInfoUIComponent, canActivate: [AuthGuardService]},
  {path: 'carservice', component: CarserviceUiComponent, canActivate: [AuthGuardService]},
  {path: 'cardata', component: CarDataUiComponent, canActivate: [AuthGuardService]},
  {path: 'review', component: ReviewComponent, canActivate: [AuthGuardService]},
  {path: '', component: HomeComponent, canActivate: [AuthGuardService]},
  {path: 'policy', component: PolicyUiComponent, canActivate: [AuthGuardService]},
  {path: 'hospital', component: HospitalUIComponent, canActivate: [AuthGuardService]},
  {path: 'propertypolicy', component: PropertyPolycyUiComponent, canActivate: [AuthGuardService]},
  {path: 'beneficiary', component: BeneficiaryUIComponent, canActivate: [AuthGuardService]},
  {path: 'claim', component: ClaimUiComponent, canActivate: [AuthGuardService]},
  {path: 'health', component: CustomerHealthUIComponent, canActivate: [AuthGuardService]},


];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ReviewComponent,
    LoginUIComponent,
    CarDataUiComponent,
    PaymentUIComponent,
    CarserviceUiComponent,
    HospitalUIComponent,
    PolicyUiComponent,
    PropertyPolycyUiComponent,

    CustomerInfoUIComponent,
    BeneficiaryUIComponent,
    BeneficiaryPopupComponent,
    ClaimUiComponent,
    CustomerHealthUIComponent

  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    ScrollingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    MatFormFieldModule,
    RouterModule.forRoot(routes)
  ],
  entryComponents: [
    BeneficiaryPopupComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
