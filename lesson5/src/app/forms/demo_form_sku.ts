import { Component } from '@angular/core';
import {$} from 'protractor';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'demo-form-sku',
  template: `
  <div class="ui raised segment">
    <h2 class="ui header">Demo Form: Information</h2>
    <form #f="ngForm" (ngSubmit)="onSubmit(f.value)" class="ui form">
      <div class="field">
        <label for="nameInput">Name</label>
        <input type="text" id="nameInput" placeholder="Name" name="name" ngModel>
      </div>
      <div class="field">
        <label for="emailInput">Email</label>
        <input type="text" id="emailInput" placeholder="Email" name="email" ngModel>
      </div>
      <button type="submit" class="btn btn-success">Submit</button>
      
    </form>
  </div>`
})
// tslint:disable-next-line:component-class-suffix
export class DemoFormSku {
  onSubmit(form: any): void {
    alert(form.name);
    alert(form.email);
    console.log('you submitted value:', form);
  }
}
