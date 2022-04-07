import { HttpErrorResponse } from '@angular/common/http';
import { Component} from '@angular/core';
import { TextAnalyze } from './text-analyze';
import { TextAnalyzeService } from './text-analyze.service';

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

  constructor(private textAnalyzeService: TextAnalyzeService) {
    this.analyze = new Map();
    this.textToAnalyze = new TextAnalyze();
    this.text = '';
    this.mode = '';
    this.connection = '';
  }

  public getMap(): void {
    this.textToAnalyze.mode = this.mode;
    this.textToAnalyze.text = this.text;

    if (this.connection === 'offline') {
      this.textAnalyzeService.getMapOfVowelsOrConsonantsOnline(this.textToAnalyze).subscribe(
        (response: Map<string, number>) => {
          this.analyze = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    } else if (this.connection === 'online') {
      this.analyze = this.textAnalyzeService.getMapOfVowelsOrConsonantsOffline(this.textToAnalyze);
    } else {
      console.log('Not found connection: ' + this.connection);
    }
  }

  onTextChange(value: any) {
    this.text = value;
  }

}
