import { NgModule } from '@angular/core';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {LayoutModule} from '@angular/cdk/layout';

const MaterialComponents = [
    MatButtonToggleModule,
    MatToolbarModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    LayoutModule,
];

@NgModule({
    imports: [MaterialComponents],
    exports: [MaterialComponents],
  })
  export class MaterialModule {}