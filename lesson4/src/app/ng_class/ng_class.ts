import {Component} from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'ng-class-sample-app',
  template:
      `<div [ngClass]="{bordered: false}">This is never bordered</div>
       <div [ngClass]="{bordered: true}">This is always bordered</div>
       <div [ngClass]="{bordered: isBordered}">Using object literal .Bordered {{ isBordered ? "ON":"OFF"}}</div>
       <button (click)="changeBordered()">changeBordered</button>
       <div [ngClass]="classesObj">Using object var.Border {{ classesObj.bordered ? "ON":"OFF"}}</div>
       <div class="base" [ngClass]="['blue','round']" ></div>
  `,
  styleUrls: ['./ng_class_style.css']
})
// tslint:disable-next-line:component-class-suffix
export class NgClassSampleApp {
  isBordered: boolean;
  // tslint:disable-next-line:ban-types
  classesObj: object;
  classList: string[];
  // @ts-ignore
  constructor() {
    this.isBordered = false;
    this.classesObj = {
      bordered: this.isBordered
    };

  }
  changeBordered(): void {
    // tslint:disable-next-line:triple-equals
    if (this.isBordered == false) {
    this.isBordered = true;
    } else {
      this.isBordered = false;
    }
    this.classesObj = {
      bordered: this.isBordered
    };
  }

}
@NgModule({
    declarations: [ NgClassSampleApp ],
    exports: [ NgClassSampleApp ],
    imports: [ BrowserModule ]
  })
export class NgClassSampleAppModule {}

