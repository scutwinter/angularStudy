import { Component } from '@angular/core';
import {$} from 'protractor';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'demo-form-sku',
  template: `
  <div class="ui raised segment">
    <h2 class="ui header">Demo Form: Sku</h2>
    <form #f="ngForm" (ngSubmit)="onSubmit(f.value)" class="ui form">
      <div class="field">
        <label for="skuInput">SKU</label>
        <input type="text" id="skuInput" placeholder="SKU" name="sku" ngModel>
      </div>
      <button type="submit" class="btn btn-success">Submit</button>
      
    </form>
  </div>`
})
// tslint:disable-next-line:component-class-suffix
export class DemoFormSku {
  onSubmit(form: any): void {
    alert(form.sku);
    console.log('you submitted value:',form);
  }
}
