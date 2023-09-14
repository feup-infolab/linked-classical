<div class="relative mx-2">
  <span class="text-lg font-semibold">Style Periods</span>
  <button class="collapse_button absolute top-0 right-0 transition-transform duration-400 transform hover:text-gray-dark">
    @svg('heroicon-s-chevron-up', 'h-7 w-7')
  </button>
  <div class="relative overflow-hidden transition-max-h ease-out duration-300">
    <ul class="mt-4  text-base">
      @forelse($stylePeriods as $stylePeriod)
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Style Period</div>
          <div class="w-4/6 font-thin">
            <a href="{{ env('APP_URL') . '/resource/' . $stylePeriod["stylePeriodUuid"] }}" class="underline">
              {{ $stylePeriod["stylePeriodURI"] }}
            </a>
          </div>
        </li>
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Title</div>
          <div class="w-4/6 font-thin">
            {{ $stylePeriod["title"] }}
          </div>
        </li>
      @empty
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Style Periods</div>
          <div class="w-4/6 font-thin text-gray">No data available</div>
        </li>
      @endforelse
    </ul>
  </div>
</div>
