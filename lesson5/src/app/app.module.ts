import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule
} from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
import { DemoFormSku } from './forms/demo_form_sku';
import { DemoFormSkuBuilder } from './forms/demo_form_sku_with_builder';
import { DemoFormWithValidationsExplicit} from './forms/demo_form_with_validations_explicit';

@NgModule({
  declarations: [
    DemoFormSku,
    DemoFormSkuBuilder,
    DemoFormWithValidationsExplicit
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [
    DemoFormSku,
    DemoFormSkuBuilder,
    DemoFormWithValidationsExplicit]
})
export class AppModule { }
