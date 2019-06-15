import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule
} from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
import { DemoFormSku } from './forms/demo_form_sku';

@NgModule({
  declarations: [
    DemoFormSku
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [ DemoFormSku]
})
export class AppModule { }
