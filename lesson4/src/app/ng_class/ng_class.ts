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
       <button class="ui button primary" (click)="changeBordered()">changeBordered</button>
       <div [ngClass]="classesObj">Using object var.Border {{ classesObj.bordered ? "ON":"OFF"}}</div>
       <div class="base" [ngClass]="['blue','round']" >this will always have a blue background and round corners</div>
       <div class="ui input" >
         <input type="checkbox" [checked]="classList.indexOf('blue')>1"
         (click)="changeCheced('blue')">
         <span>Blue</span>
       </div>
        <div class="ui input" >
          <input type="checkbox" [checked]="classList.indexOf('round')>1"
                 (click)="changeCheced('round')">
          <span>Round</span>
        </div>
       <div class="base" [ngClass]="classList">
         This is {{classList.indexOf('blue') > -1 ? "" : "Not"}} blue and
         {{ classList.indexOf('round') > -1 ? "" : "Not"}} round
       </div>

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
    this.classList=['blue','round'];


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
  changeCheced(cssString: string ): void {
    // tslint:disable-next-line:prefer-const
    let pos: number = this.classList.indexOf(cssString);
    if (pos > -1) {
      this.classList.splice(pos, 1);
    } else {
      this.classList.push(cssString);
    }
  }

}
@NgModule({
    declarations: [ NgClassSampleApp ],
    exports: [ NgClassSampleApp ],
    imports: [ BrowserModule ]
  })
export class NgClassSampleAppModule {}

