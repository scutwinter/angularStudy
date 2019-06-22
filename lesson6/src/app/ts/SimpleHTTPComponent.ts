import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'simple-http',
  template: `
  <h2>Basic Request</h2>
  <button class="ui button primary" type="button" (click)="makeRequest()">Make Request</button>
  <div *ngIf="loading">loading...</div>
    <pre>{{data | json}}</pre>
  <button class="ui button primary" type="button" (click)="makeRequestFun()">Make Request Map</button>
  <div *ngIf="loading">loading...</div>
  <pre>{{ title }}</pre>
  `
  })
export class SimpleHTTPComponent{
  // tslint:disable-next-line:ban-types
  data: Object;
  // tslint:disable-next-line:ban-types
  title: String;
  loading: boolean;

  constructor(private http: HttpClient){
  }

  makeRequest(): void{
    this.loading = true;
    this.http.get('http://jsonplaceholder.typicode.com/posts/1')
      .subscribe((res) => {
        this.data = res;
        this.loading = false;
      });
  }

  // @ts-ignore
  // tslint:disable-next-line:ban-types
  makeRequestMap(): Observable<String> {
    return this.http.get('http://jsonplaceholder.typicode.com/posts/1')
      .pipe(map((res) => {
        return (res as any).title;
    })
  );
  }

  makeRequestFun(): void {
    this.loading = true;
    this.makeRequestMap()
      .subscribe((res) => {
        this.title = res;
        this.loading = false;
      });
  }
}
