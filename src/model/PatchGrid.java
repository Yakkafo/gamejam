/* @author william */

package model;

import math.iRect;
import math.iVect;

class PatchGrid
{
    /// ATTRIBUTES
    private iVect patch_size;
    private Patch[][] patches;

    /// METHODS

    // creation
    PatchGrid(iRect area, iVect n_patches)
    {
        // Calculate size of patches
        patch_size = new iVect(area.width/n_patches.x,area.height/n_patches.y);

        // Create patch array
        patches = new Patch[n_patches.x][n_patches.y];
        for(int ln = 0; ln < patches.length; ln++)
            for(int cl = 0; cl < patches[ln].length; cl++)
                patches[ln][cl] = new Patch();
    }

    //query
    public Patch getPatch(iVect where)
    {
        return patches[where.y/patch_size.x][where.x/patch_size.y];
    }
            
}
