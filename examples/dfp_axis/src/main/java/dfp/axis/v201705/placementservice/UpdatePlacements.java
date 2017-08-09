// Copyright 2015 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package dfp.axis.v201705.placementservice;

import com.beust.jcommander.Parameter;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api;
import com.google.api.ads.common.lib.utils.examples.CodeSampleParams;
import com.google.api.ads.dfp.axis.factory.DfpServices;
import com.google.api.ads.dfp.axis.utils.v201705.StatementBuilder;
import com.google.api.ads.dfp.axis.v201705.Placement;
import com.google.api.ads.dfp.axis.v201705.PlacementPage;
import com.google.api.ads.dfp.axis.v201705.PlacementServiceInterface;
import com.google.api.ads.dfp.lib.client.DfpSession;
import com.google.api.ads.dfp.lib.utils.examples.ArgumentNames;
import com.google.api.client.auth.oauth2.Credential;
import com.google.common.collect.Iterables;
import java.util.Arrays;

/**
 * This example updates a placement to allow AdSense targeting. To determine
 * which placements exist, run GetAllPlacements.java. To update a placement
 * by adding an ad unit to it, see AssignAdUnitsToPlacement.java.
 *
 * Credentials and properties in {@code fromFile()} are pulled from the
 * "ads.properties" file. See README for more info.
 */
public class UpdatePlacements {

  private static class UpdatePlacementsParams extends CodeSampleParams {
    @Parameter(names = ArgumentNames.PLACEMENT_ID, required = true,
        description = "The ID of the placement to update.")
    private Long placementId;
  }

  public static void runExample(DfpServices dfpServices, DfpSession session, long placementId)
      throws Exception {
    // Get the PlacementService.
    PlacementServiceInterface placementService =
        dfpServices.get(session, PlacementServiceInterface.class);

    // Create a statement to only select a single placement by ID.
    StatementBuilder statementBuilder = new StatementBuilder()
        .where("id = :id")
        .orderBy("id ASC")
        .limit(1)
        .withBindVariableValue("id", placementId);

    // Get the placement.
    PlacementPage page =
        placementService.getPlacementsByStatement(statementBuilder.toStatement());

    Placement placement = Iterables.getOnlyElement(Arrays.asList(page.getResults()));

    placement.setDescription("This placement contains all leaderboards.");

    // Update the placement on the server.
    Placement[] placements = placementService.updatePlacements(new Placement[] {placement});

    for (Placement updatedPlacement : placements) {
      System.out.printf("Placement with ID %d and name '%s' was updated.%n",
          updatedPlacement.getId(), updatedPlacement.getName());
    }
  }

  public static void main(String[] args) throws Exception {
    // Generate a refreshable OAuth2 credential.
    Credential oAuth2Credential = new OfflineCredentials.Builder()
        .forApi(Api.DFP)
        .fromFile()
        .build()
        .generateCredential();

    // Construct a DfpSession.
    DfpSession session = new DfpSession.Builder()
        .fromFile()
        .withOAuth2Credential(oAuth2Credential)
        .build();

    DfpServices dfpServices = new DfpServices();

    UpdatePlacementsParams params = new UpdatePlacementsParams();
    if (!params.parseArguments(args)) {
      // Either pass the required parameters for this example on the command line, or insert them
      // into the code here. See the parameter class definition above for descriptions.
      params.placementId = Long.parseLong("INSERT_PLACEMENT_ID_HERE");
    }

    runExample(dfpServices, session, params.placementId);
  }
}