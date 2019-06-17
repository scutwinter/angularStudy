import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'simple-http',
  template: `
  <h2>Basic Request</h2>
  <button class="ui button primary" type="button" (click)="makeRequest()">Make Request</button>
  <div *ngIf="loading">loading...</div>
    <pre>{{data | json}}</pre>
  `
  })
export class SimpleHTTPComponent{
  // tslint:disable-next-line:ban-types
  data: Object;
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
}
