import { HttpErrorResponse } from '@angular/common/http';
import { Component} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { TextAnalyze } from './text-analyze';
import { TextAnalyzeService } from './text-analyze.service';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public analyze: Map<string, number>;
  public textToAnalyze: TextAnalyze;
  public text: string;
  public mode: string;
  public connection: string;
  public loading: boolean;
  public inputFormControl: FormControl;


  constructor(private textAnalyzeService: TextAnalyzeService, public dialog: MatDialog) {
    this.analyze = new Map();
    this.textToAnalyze = new TextAnalyze();
    this.text = '';
    this.mode = 'vowels';
    this.connection = 'offline';
    this.loading = false;
    this.inputFormControl = new FormControl('', [Validators.required])
  }

  public getMap(): void {
    this.textToAnalyze.mode = this.mode;
    this.textToAnalyze.text = this.text;

    if (this.connection === 'online') {
      this.loading = true;
      this.textAnalyzeService.getMapOfVowelsOrConsonantsOnline(this.textToAnalyze).subscribe(
        (response: Map<string, number>) => {
          this.loading = false;
          this.analyze = response;
        },
        (error: HttpErrorResponse) => {
          const dialogRef = this.dialog.open(HttpClientConnectionErrorDialog, { disableClose: true });
          dialogRef.afterClosed().subscribe(() => this.loading = false);
        }
      );
    } else if (this.connection === 'offline') {
      this.analyze = this.textAnalyzeService.getMapOfVowelsOrConsonantsOffline(this.textToAnalyze);
    } else {
      console.log('Not found connection: ' + this.connection);
    }
  }

  onTextChange(value: any) {
    this.text = value;
  }

}

@Component({
  selector: 'http-client-connection-error-dialog',
  templateUrl: './http-client-connection-error.component.html',
})
export class HttpClientConnectionErrorDialog {}
