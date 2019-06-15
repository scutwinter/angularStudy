import {Component} from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'ng-for-sample-app',
  template:
    `
    <h4 class="ui horizontal divider header">
    Simple list of strings
    </h4>
    <div class="ui bulleted list" *ngFor="let c of cities">
      <div class="item">{{ c }}</div>
    </div>
    <h4 class="ui horizontal divider header">
      List of objects
    </h4>
    <table class="ui celled table">
      <thead>
      <tr>
        <th>Name</th>
        <th>Age</th>
        <th>City</th>
      </tr>
      </thead>
      <tr *ngFor="let p of people">
        <td>{{p.name}}</td>
        <td>{{p.age}}</td>
        <td>{{p.city}}</td>
      </tr>
    </table>
    <h4 class="ui horizontal divider header">
      Nested data
    </h4>
    <div *ngFor=" let item of peopleByCity">
      <h2 class="ui header">{{item.city}}</h2>
      <table class="ui celled table">
        <thead>
        <tr>
          <th>No.</th>
          <th>Name</th>
          <th>Age</th>
        </tr>
        </thead>
        <tr *ngFor="let p of item.people; let num = index">
          <td>{{num+1}}</td>
          <td>{{p.name}}</td>
          <td>{{p.age}}</td>
        </tr>
      </table>
    </div>
  `
})
// tslint:disable-next-line:component-class-suffix
export class NgForSampleApp {
  cities: string[];
  people: Object;
  peopleByCity: Object;

  // @ts-ignore
  constructor() {
    this.cities = ['BeiJing', 'ShangHai', 'GuangZhou', 'ShenZhen'];
    this.people = [
      { name : 'Winter', age : 35 , city : 'GuangZhou'},
      { name : 'Anderson', age : 21 , city : 'ShenZhen'},
      { name : 'Peter', age : 32 , city : 'ShangHai'},
      { name : 'John', age : 31 , city : 'BeiJing'},
    ];
    this.peopleByCity = [
      { city: 'GuangZhou',
        people: [
          { name : 'Winter', age : 35 },
          { name : 'Anderson', age : 21}
        ]
      },
      { city: 'ShenZhen',
        people: [
          { name : 'Peter', age : 35 },
          { name : 'John', age : 21}
        ]
      }
    ];
  }
}
@NgModule({
  declarations: [ NgForSampleApp ],
  exports: [ NgForSampleApp ],
  imports: [ BrowserModule ]
})
export class NgForSampleAppModule {}

