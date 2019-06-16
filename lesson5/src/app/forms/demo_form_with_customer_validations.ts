import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  FormControl
} from '@angular/forms';
function skuValidator(control: FormControl):{ [s: string]: boolean } {
  if (!control.value.match(/^123/)) {
    return {invalidSku: true};
  }

}
// @ts-ignore
@Component({
  // tslint:disable-next-line:component-selector
  selector: 'demo-form-with-custom-validations',
  template: `
  <div class="ui raised segment">
    <h2 class="ui header">Demo Form: with validations (Customer)</h2>
    <div *ngIf="!sku.valid"
         class="ui error message">SKU is invalid</div>
    <div *ngIf="sku.hasError('required')"
         class="ui error message">SKU is required</div>
    <div *ngIf="!myForm.valid"
         class="ui error message">Form is invalid</div>
    <div *ngIf="sku.hasError('invalidSku')"
         class="ui error message">SKU must begin with <span>123</span></div>
    <div class="ui info message">
      this field value is :{{fieldValue}}
    </div>
    <form [formGroup]="myForm"
          (ngSubmit)="onSubmit(myForm.value)"
          class="ui form">

      <div class="field"
          [class.error]="!sku.valid && sku.touched">
        <label for="skuInput">SKU</label>
        <input type="text"
               id="skuInput"
               placeholder="SKU"
               [formControl]="myForm.controls['sku']"
                [(ngModel)]="fieldValue">
      </div>
      <button type="submit" class="ui button">Submit</button>
    </form>

  </div>
  `
})
// tslint:disable-next-line:component-class-suffix
export class DemoFormWithCustomValidations {
  myForm: FormGroup;
  sku: AbstractControl;
  fieldValue: string ;

  constructor(fb: FormBuilder) {
    this.myForm = fb.group({
      'sku':  ['', Validators.compose([Validators.required, skuValidator])]
    });

    this.sku = this.myForm.controls['sku'];
    this.sku.valueChanges.subscribe(
      (value:string) => {
        console.log('sku changed to:', value);
      } );
    this.sku.valueChanges.subscribe(
      (form: any) => {
        console.log('form changed to:', form);
      } );
  }

  onSubmit(value: string): void {
    console.log('you submitted value: ', this.sku.valid);
  }
}
