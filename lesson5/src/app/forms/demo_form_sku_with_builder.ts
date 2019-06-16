import { Component } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  // tslint:disable-next-line:component-selector
  selector : 'demo-form-sku-builder',
  template : `
    <div class="ui raised segment">
      <h2 class="ui header">Demo Form : information with Builder</h2>
      <div *ngIf="!name.valid" class="ui error message">Name is invalid</div>
      <div *ngIf="name.hasError('required')"
           class="ui error message">Name is required</div>
      <div *ngIf="!email.valid" class="ui error message">Email is invalid</div>
      <div *ngIf="email.hasError('required')"
           class="ui error message">Name is required</div>
      <form [formGroup]="myForm" (ngSubmit)="onSubmit(myForm.value)" class="ui form">
        <div class="field"  [class.error]="!name.valid && name.touched">
          <label for="nameInput">Name</label>
          <input type="text" id="nameInput" placeholder="please input your name" [formControl]="myForm.controls['name']">
        </div>
        <div class="field" [class.error]="!email.valid && email.touched">
          <label for="emailInput">Email</label>
          <input type="text" id="emailInput" placeholder="please input your email" [formControl]="myForm.controls['email']">
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
      </form>
    </div>`
})
// tslint:disable-next-line:component-class-suffix
export class DemoFormSkuBuilder {
  myForm: FormGroup;
  name: AbstractControl;
  email: AbstractControl;

  constructor(fb: FormBuilder) {
    this.myForm = fb.group({
      'name': ['', Validators.required],
      'email': ['', Validators.email]
    });
    this.name = this.myForm.controls['name'];
    this.email =  this.myForm.controls['email'];
  }
  onSubmit(value: string): void{
    // alert(value.name);
    // alert(value.email);
    console.log('you submitted value:', value);
  }
}
