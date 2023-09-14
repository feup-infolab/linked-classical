<div>
  <div class="mb-5 mx-2">
    @include('web.composer.components.form_fields.external_entity')
  </div>
  <hr class="my-4">
  <div class="mx-2 relative mb-5 ">
    <span class="text-lg font-semibold">External Entities</span>
    <button class="collapse_button absolute top-0 right-0 justify-end transition-transform duration-400 transform">
      @svg('heroicon-s-chevron-up', 'h-7 w-7')
    </button>

    <div class="relative overflow-hidden transition-max-h ease-out duration-300">
      <div class="my-6 bg-gray-lightest rounded relative">
        <div class="px-8 py-4 mt-5">
          <div class="my-4 flex">
            <!-- Table -->
            <div class="w-full bg-white">
              <table class="table table-bordered table-striped rounded-lg text-sm">
                <tr>
                  <th>Entities</th>
                  <th></th>
                </tr>
                @forelse($links as $link)
                  @if(strpos($link, "http://www.wikidata.org") !== false)
                  <tr>
                    <td>
                        <div>
                          <div>
                            <div><a href="{{ $link }}" class="underline font-bold" target="_blank">{{ $externalInfoSummary["https://www.wikidata.org/"]["labelEn"] }}</a>,&nbsp;{{ $externalInfoSummary["https://www.wikidata.org/"]["description"] }}</div>
                            <div class="text-base my-2" style="font-size: 0.98em;"><a href="{{ $link }}" target="_blank">{{$link}}</a></div>
                            @php
                              $instanceOfArraySize = count($externalInfoSummary["https://www.wikidata.org/"]["instanceOf"]);
                            @endphp
                            @for($i = 0; $i < $instanceOfArraySize; $i++)
                              <div>
                                <span>instance of</span>
                                <span>
                                  <a href="{{ $externalInfoSummary["https://www.wikidata.org/"]["instanceOf"][$i] }}" class="underline" target="_blank">
                                    <span>{{ $externalInfoSummary["https://www.wikidata.org/"]["instanceOfLabel"][$i] }}</span>
                                  </a>
                                </span>
                              </div>
                            @endfor
                          </div>
                        </div>
                    </td>
                    <td class="text-center">
                      <button class="remove-entity-link" title="Remove External Entity Link" data-uri="{{ $link }}">
                        @svg('heroicon-s-x', 'h-5 w-5')
                      </button>
                    </td>
                  </tr>
                  @endif
                @empty
                  <tr>
                    <td class="italic text-gray-dark text-center" colspan="2">No External Entities</td>
                  </tr>
                @endforelse
              </table>
            </div>
          </div>
        </div>
      </div>
      <span id="remove-error-message" class="mb-2" style="color: red"></span>
    </div>
  </div>

  @if(isset($externalInfoboxesInfo['https://www.wikidata.org/']))
    @include('web.composer.components.show.external_infoboxes.wikidata_infobox', ['info' => $externalInfoboxesInfo['https://www.wikidata.org/']])
  @endif


</div>
